import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Student } from './student.model';
import { StudentService } from './student.service';

@Component({
  selector: 'app-root',
  imports: [CommonModule, FormsModule],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App implements OnInit {
  estudiantes: Student[] = [];
  estudiante: Student = this.crearEstudianteVacio();
  idBusqueda = '';
  editando = false;
  cargando = false;
  mensaje = '';
  error = '';

  constructor(private readonly studentService: StudentService) {}

  ngOnInit(): void {
    this.listarEstudiantes();
  }

  listarEstudiantes(): void {
    this.cargando = true;
    this.limpiarMensajes();

    this.studentService.listar().subscribe({
      next: (respuesta) => {
        this.estudiantes = respuesta;
        this.cargando = false;
      },
      error: () => {
        this.error = 'No se pudo listar estudiantes. Revisa que el backend este encendido.';
        this.cargando = false;
      }
    });
  }

  buscarEstudiante(): void {
    const id = Number(this.idBusqueda);

    if (!id) {
      this.error = 'Ingresa un ID valido para buscar.';
      return;
    }

    this.cargando = true;
    this.limpiarMensajes();

    this.studentService.buscarPorId(id).subscribe({
      next: (respuesta) => {
        this.estudiante = { ...respuesta };
        this.editando = true;
        this.mensaje = 'Estudiante encontrado.';
        this.cargando = false;
      },
      error: () => {
        this.error = 'No se encontro un estudiante con ese ID.';
        this.cargando = false;
      }
    });
  }

  guardarEstudiante(): void {
    this.limpiarMensajes();

    if (this.editando && this.estudiante.id) {
      this.actualizarEstudiante();
      return;
    }

    this.registrarEstudiante();
  }

  editarEstudiante(estudiante: Student): void {
    this.estudiante = { ...estudiante };
    this.editando = true;
    this.limpiarMensajes();
  }

  eliminarEstudiante(id?: number): void {
    if (!id || !confirm('Deseas eliminar este estudiante?')) {
      return;
    }

    this.cargando = true;
    this.limpiarMensajes();

    this.studentService.eliminar(id).subscribe({
      next: () => {
        this.mensaje = 'Estudiante eliminado.';
        this.limpiarFormulario();
        this.listarEstudiantes();
      },
      error: () => {
        this.error = 'No se pudo eliminar el estudiante.';
        this.cargando = false;
      }
    });
  }

  limpiarFormulario(): void {
    this.estudiante = this.crearEstudianteVacio();
    this.editando = false;
    this.idBusqueda = '';
  }

  private registrarEstudiante(): void {
    this.cargando = true;

    this.studentService.registrar(this.estudiante).subscribe({
      next: () => {
        this.mensaje = 'Estudiante registrado.';
        this.limpiarFormulario();
        this.listarEstudiantes();
      },
      error: () => {
        this.error = 'No se pudo registrar. Revisa los datos ingresados.';
        this.cargando = false;
      }
    });
  }

  private actualizarEstudiante(): void {
    this.cargando = true;

    this.studentService.actualizar(this.estudiante.id!, this.estudiante).subscribe({
      next: () => {
        this.mensaje = 'Estudiante actualizado.';
        this.limpiarFormulario();
        this.listarEstudiantes();
      },
      error: () => {
        this.error = 'No se pudo actualizar. Revisa los datos ingresados.';
        this.cargando = false;
      }
    });
  }

  private limpiarMensajes(): void {
    this.mensaje = '';
    this.error = '';
  }

  private crearEstudianteVacio(): Student {
    return {
      dni: '',
      firstName: '',
      lastName: '',
      promotion: '',
      date: ''
    };
  }
}
