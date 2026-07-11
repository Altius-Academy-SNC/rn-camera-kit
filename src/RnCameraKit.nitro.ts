import type { HybridObject } from 'react-native-nitro-modules';

export interface ScannedDocument {
  /**
   * file:// URIs to each captured page image, in order.
   */
  pageUris: string[];
  /**
   * file:// URI to an assembled multi-page PDF, when the platform scanner
   * produced one (Android ML Kit Document Scanner always does; not
   * guaranteed on every platform/mode).
   */
  pdfUri?: string;
}

export interface RnCameraKit extends HybridObject<{
  ios: 'swift';
  android: 'kotlin';
}> {
  /**
   * Launches the platform's native document scanner (Google ML Kit
   * Document Scanner on Android, VisionKit on iOS) and resolves once the
   * user finishes scanning. Rejects if the user cancels or the platform
   * scanner isn't available.
   */
  scanDocument(): Promise<ScannedDocument>;
}
