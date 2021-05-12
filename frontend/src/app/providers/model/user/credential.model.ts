export interface Credential {
  username: string;
  roles: string[];
  access_token: string;
  token_type: string;
  expires_in: number;
}
