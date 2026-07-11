# rn-camera-kit

Document scanning and KYC face verification for React Native — bare RN + Metro only, no Expo. Full native platform features: Google ML Kit Document Scanner (Android), VisionKit `VNDocumentCameraViewController` (iOS), ML Kit Face Detection for liveness.

Built as a [Nitro Module](https://nitro.margelo.com/) (Kotlin + Swift, no Objective-C/Java bridge boilerplate).

## Status

- **Android document scanning: implemented and verified**, including a real emulator run (tap "Scan document" → Google ML Kit's document scanner → resolves with page/PDF `file://` URIs).
- **iOS document scanning: not implemented.** `scanDocument()` currently rejects with "not implemented yet" — the Swift side compiles (verified in CI on `macos-latest`), but the VisionKit (`VNDocumentCameraViewController`) implementation itself hasn't been written. This machine has no Xcode, so iOS work has to happen elsewhere.
- **KYC: not started.**

Companion package: [django-camera-kit](https://github.com/Altius-Academy-SNC/django-camera-kit) — the KYC module here is intentionally hard-coupled to a `django-camera-kit` backend (no generic backend abstraction), matching that package's `/kyc/verify/` endpoint contract.

### A note on testing Android document scanning

`GmsDocumentScanning` dynamically downloads its scanner UI as a Play Services module on first use. On an emulator image without a full/recent Play Services (e.g. a plain "Google APIs" AVD, `PlayStore.enabled=no` with `tag.id=google_apis`), this fails with `MlKitException: Feature not available in the current version of the Google Play services` — that's an emulator environment issue, not a bug. Use a `google_apis_playstore`-tagged AVD (or a real device) instead.

## Development

```sh
yarn install
yarn typecheck
yarn lint
yarn test

# Build the example app
yarn example android
yarn example ios   # requires macOS + Xcode — CI builds this on macos-latest runners
```

## Installation

Not yet published. Once released:

```sh
npm install rn-camera-kit react-native-nitro-modules
```

> `react-native-nitro-modules` is a required peer dependency.

## License

MIT
