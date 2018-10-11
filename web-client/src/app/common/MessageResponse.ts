export class MessageResponse {
  constructor(message: string, error: boolean) {
    this.message = message;
    this.error = error;
  }
  error: boolean;
  message: string;
}
