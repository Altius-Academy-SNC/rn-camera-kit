# rn-camera-kit

Document scanning and KYC face verification for React Native — bare RN + Metro only, no Expo. Full native platform features: Google ML Kit Document Scanner (Android), VisionKit `VNDocumentCameraViewController` (iOS), ML Kit Face Detection for liveness.

## Status

Not started. Development begins once `django-camera-kit` phase 1 (scan) and phase 4 (KYC) have a stable API to build against — the KYC module here is hard-coupled to a `django-camera-kit` backend by design (no generic backend abstraction).

Full scaffold (native modules, `react-native-vision-camera` setup, dev client) will be created when this phase starts, to avoid boilerplate going stale before it's used.

## License

MIT
