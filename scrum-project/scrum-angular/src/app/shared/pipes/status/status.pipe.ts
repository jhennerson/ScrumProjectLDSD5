import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'status'
})
export class StatusPipe implements PipeTransform {

  transform(value: string): string {
    switch (value) {
      case 'TO_DO':
        return 'A fazer';
      case 'IN_PROGRESS':
        return 'Em progresso';
      case 'DONE':
        return 'Finalizada';
      default:
        throw new Error(`Status inválido: ${value}`);
    }
  }
}
