import { useState } from 'react';
import { Button, ScrollView, StyleSheet, Text, View } from 'react-native';
import { scanDocument, type ScannedDocument } from 'rn-camera-kit';

export default function App() {
  const [result, setResult] = useState<ScannedDocument | null>(null);
  const [error, setError] = useState<string | null>(null);

  const onScanPress = async () => {
    setError(null);
    try {
      const scanned = await scanDocument();
      setResult(scanned);
    } catch (e) {
      setError(e instanceof Error ? e.message : String(e));
    }
  };

  return (
    <ScrollView contentContainerStyle={styles.container}>
      <Button title="Scan document" onPress={onScanPress} />
      {error != null && <Text style={styles.error}>{error}</Text>}
      {result != null && (
        <View>
          <Text>{result.pageUris.length} page(s)</Text>
          {result.pageUris.map((uri) => (
            <Text key={uri}>{uri}</Text>
          ))}
          {result.pdfUri != null && <Text>PDF: {result.pdfUri}</Text>}
        </View>
      )}
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  container: {
    flexGrow: 1,
    alignItems: 'center',
    justifyContent: 'center',
    gap: 12,
    padding: 24,
  },
  error: {
    color: 'red',
  },
});
