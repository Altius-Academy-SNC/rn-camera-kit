class RnCameraKit: HybridRnCameraKitSpec {
    func scanDocument() throws -> Promise<ScannedDocument> {
        // VisionKit (VNDocumentCameraViewController) implementation not
        // written yet — see the Android implementation in
        // android/src/main/java/com/margelo/nitro/rncamerakit/RnCameraKit.kt
        // for the equivalent flow this needs to mirror.
        return Promise.rejected(
            withError: NSError(
                domain: "RnCameraKit",
                code: -1,
                userInfo: [NSLocalizedDescriptionKey: "scanDocument() is not implemented on iOS yet."]
            )
        )
    }
}
