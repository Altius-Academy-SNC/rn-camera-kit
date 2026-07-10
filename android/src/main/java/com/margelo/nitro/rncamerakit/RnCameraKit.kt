package com.margelo.nitro.rncamerakit
  
import com.facebook.proguard.annotations.DoNotStrip

@DoNotStrip
class RnCameraKit : HybridRnCameraKitSpec() {
  override fun multiply(a: Double, b: Double): Double {
    return a * b
  }
}
