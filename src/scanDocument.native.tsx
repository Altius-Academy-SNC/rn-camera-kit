import { NitroModules } from 'react-native-nitro-modules';
import type { RnCameraKit, ScannedDocument } from './RnCameraKit.nitro';

const RnCameraKitHybridObject =
  NitroModules.createHybridObject<RnCameraKit>('RnCameraKit');

export function scanDocument(): Promise<ScannedDocument> {
  return RnCameraKitHybridObject.scanDocument();
}
