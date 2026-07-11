package com.margelo.nitro.rncamerakit

import android.app.Activity
import android.content.Intent
import com.facebook.proguard.annotations.DoNotStrip
import com.facebook.react.bridge.BaseActivityEventListener
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions
import com.google.mlkit.vision.documentscanner.GmsDocumentScanning
import com.google.mlkit.vision.documentscanner.GmsDocumentScanningResult
import com.margelo.nitro.NitroModules
import com.margelo.nitro.core.Promise

// Arbitrary but fixed request code identifying our scan intent among any
// other onActivityResult calls the host activity might receive.
private const val SCAN_REQUEST_CODE = 5112

@DoNotStrip
class RnCameraKit : HybridRnCameraKitSpec() {

  override fun scanDocument(): Promise<ScannedDocument> {
    val promise = Promise<ScannedDocument>()

    val reactContext = NitroModules.applicationContext
    if (reactContext == null) {
      promise.reject(IllegalStateException("No React application context available"))
      return promise
    }

    val activity = reactContext.currentActivity
    if (activity == null) {
      promise.reject(IllegalStateException("No current Activity available"))
      return promise
    }

    val options =
      GmsDocumentScannerOptions.Builder()
        .setGalleryImportAllowed(false)
        .setResultFormats(
          GmsDocumentScannerOptions.RESULT_FORMAT_JPEG,
          GmsDocumentScannerOptions.RESULT_FORMAT_PDF
        )
        .setScannerMode(GmsDocumentScannerOptions.SCANNER_MODE_FULL)
        .build()

    // The scanner Activity reports its result through onActivityResult, so we
    // register a one-shot listener on the ReactContext rather than a
    // registerForActivityResult() launcher -- that API requires registration
    // during the host Activity's onCreate, before it's STARTED, which we
    // have no control over from a native module invoked at an arbitrary
    // time. startIntentSenderForResult() has no such restriction.
    val listener =
      object : BaseActivityEventListener() {
        override fun onActivityResult(
          activity: Activity,
          requestCode: Int,
          resultCode: Int,
          data: Intent?,
        ) {
          if (requestCode != SCAN_REQUEST_CODE) return
          reactContext.removeActivityEventListener(this)

          if (resultCode != Activity.RESULT_OK) {
            promise.reject(Exception("Document scan was cancelled"))
            return
          }

          val result = GmsDocumentScanningResult.fromActivityResultIntent(data)
          if (result == null) {
            promise.reject(Exception("Document scanner returned no result"))
            return
          }

          val pageUris = result.pages?.map { it.imageUri.toString() } ?: emptyList()
          val pdfUri = result.pdf?.uri?.toString()
          promise.resolve(ScannedDocument(pageUris.toTypedArray(), pdfUri))
        }
      }

    reactContext.addActivityEventListener(listener)

    GmsDocumentScanning.getClient(options)
      .getStartScanIntent(activity)
      .addOnSuccessListener { intentSender ->
        try {
          activity.startIntentSenderForResult(
            intentSender,
            SCAN_REQUEST_CODE,
            null,
            0,
            0,
            0,
          )
        } catch (e: Exception) {
          reactContext.removeActivityEventListener(listener)
          promise.reject(e)
        }
      }
      .addOnFailureListener { e ->
        reactContext.removeActivityEventListener(listener)
        promise.reject(e)
      }

    return promise
  }
}
