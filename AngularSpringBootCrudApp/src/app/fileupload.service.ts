import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { FileDetails } from './filedetails';

@Injectable({
  providedIn: 'root'
})
export class FileUploadService {

  private baseUrl = "http://localhost:8084/api/v1/upload"
  constructor(private httpClient: HttpClient) { }

  upload(file: File): Observable<FileDetails> {

    const formData: FormData = new FormData();
    formData.append('file', file);
    return this.httpClient.post<FileDetails>(this.baseUrl, formData);
  }
}