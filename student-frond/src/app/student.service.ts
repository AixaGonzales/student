import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Student } from './student.model';

@Injectable({
  providedIn: 'root'
})
export class StudentService {
  private readonly apiUrl = 'http://localhost:8090/v1/api/student';

  constructor(private readonly http: HttpClient) {}

  listar(): Observable<Student[]> {
    return this.http.get<Student[]>(`${this.apiUrl}/list`);
  }

  buscarPorId(id: number): Observable<Student> {
    return this.http.get<Student>(`${this.apiUrl}/find/${id}`);
  }

  registrar(estudiante: Student): Observable<Student> {
    return this.http.post<Student>(`${this.apiUrl}/register`, estudiante);
  }

  actualizar(id: number, estudiante: Student): Observable<Student> {
    return this.http.put<Student>(`${this.apiUrl}/update/${id}`, estudiante);
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${id}`);
  }
}
