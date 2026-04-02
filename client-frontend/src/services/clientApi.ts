import apiClient from './apiClient';
import { tokenService } from './tokenService';

// Types for API responses
export interface LoginRequest {
  email: string;
  password: string;
}

export interface LoginResponse {
  token: string;
  user: {
    id: string;
    email: string;
    name: string;
  };
}

export interface Client {
  id: string;
  name: string;
  email: string;
  createdAt: string;
}

export interface CreateClientRequest {
  name: string;
  email: string;
}

// Client API service
export const clientApi = {
  /**
   * Login user and store token
   */
  async login(credentials: LoginRequest): Promise<LoginResponse> {
    try {
      const response = await apiClient.post<LoginResponse>('/auth/login', credentials);
      if (response.data.token) {
        tokenService.setToken(response.data.token);
      }
      return response.data;
    } catch (error) {
      throw error;
    }
  },

  /**
   * Logout user and clear token
   */
  logout(): void {
    tokenService.clearAuth();
  },

  /**
   * Get all clients
   */
  async getClients(): Promise<Client[]> {
    try {
      const response = await apiClient.get<Client[]>('/clients');
      return response.data;
    } catch (error) {
      throw error;
    }
  },

  /**
   * Get a single client by ID
   */
  async getClient(id: string): Promise<Client> {
    try {
      const response = await apiClient.get<Client>(`/clients/${id}`);
      return response.data;
    } catch (error) {
      throw error;
    }
  },

  /**
   * Create a new client
   */
  async createClient(data: CreateClientRequest): Promise<Client> {
    try {
      const response = await apiClient.post<Client>('/clients', data);
      return response.data;
    } catch (error) {
      throw error;
    }
  },

  /**
   * Update a client
   */
  async updateClient(id: string, data: Partial<CreateClientRequest>): Promise<Client> {
    try {
      const response = await apiClient.put<Client>(`/clients/${id}`, data);
      return response.data;
    } catch (error) {
      throw error;
    }
  },

  /**
   * Delete a client
   */
  async deleteClient(id: string): Promise<void> {
    try {
      await apiClient.delete(`/clients/${id}`);
    } catch (error) {
      throw error;
    }
  },
};
