import type { ScannedDocument } from './RnCameraKit.nitro';

export function scanDocument(): Promise<ScannedDocument> {
  throw new Error("'rn-camera-kit' is only supported on native platforms.");
}
