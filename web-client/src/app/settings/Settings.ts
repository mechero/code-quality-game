export const SERVER_URL_KEY = 'QUBOO_SERVER_URL';

export function getStoredServerUrl() : string {
  return localStorage.getItem(SERVER_URL_KEY);
}

export function isServerUrlStored() : boolean {
  return localStorage.getItem(SERVER_URL_KEY) !== null;
}
