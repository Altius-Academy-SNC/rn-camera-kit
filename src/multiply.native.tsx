import { NitroModules } from 'react-native-nitro-modules';
import type { RnCameraKit } from './RnCameraKit.nitro';

const RnCameraKitHybridObject =
  NitroModules.createHybridObject<RnCameraKit>('RnCameraKit');

export function multiply(a: number, b: number): number {
  return RnCameraKitHybridObject.multiply(a, b);
}
