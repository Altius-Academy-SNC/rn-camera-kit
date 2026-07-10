# rn-camera-kit

Document scanning and KYC face verification for React Native — bare RN + Metro only, no Expo. Full native platform features: Google ML Kit Document Scanner (Android), VisionKit `VNDocumentCameraViewController` (iOS), ML Kit Face Detection for liveness.

Built as a [Nitro Module](https://nitro.margelo.com/) (Kotlin + Swift, no Objective-C/Java bridge boilerplate).

## Status

Scaffolded — package builds, lints, and the Android example compiles end-to-end (`yarn turbo run build:android`, verified locally). The actual native scan/KYC implementation (Kotlin wrapping ML Kit Document Scanner, Swift wrapping VisionKit) hasn't been written yet.

Companion package: [django-camera-kit](https://github.com/Altius-Academy-SNC/django-camera-kit) — the KYC module here is intentionally hard-coupled to a `django-camera-kit` backend (no generic backend abstraction), matching that package's `/kyc/verify/` endpoint contract.

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
