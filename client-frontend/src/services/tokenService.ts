// Token service for managing authentication tokens
const TOKEN_KEY = 'auth_token';

export const tokenService = {
  /**
   * Get the stored token from localStorage
   */
  getToken(): string | null {
    return localStorage.getItem(TOKEN_KEY);
  },

  /**
   * Store the token in localStorage
   */
  setToken(token: string): void {
    localStorage.setItem(TOKEN_KEY, token);
  },

  /**
   * Remove the token from localStorage
   */
  removeToken(): void {
    localStorage.removeItem(TOKEN_KEY);
  },

  /**
   * Check if token exists
   */
  hasToken(): boolean {
    return !!this.getToken();
  },

  /**
   * Clear all auth data
   */
  clearAuth(): void {
    this.removeToken();
  }
};
