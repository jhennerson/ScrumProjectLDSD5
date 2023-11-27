import { Pipe, PipeTransform } from '@angular/core';
import { Status } from 'src/app/enum/status.enum';

@Pipe({
  name: 'status',
})
export class StatusPipe implements PipeTransform {
  transform(value: string): string {
    switch (value) {
      case Status.ToDo:
        return 'A fazer';
      case Status.InProgress:
        return 'Em progresso';
      case Status.Done:
        return 'Finalizada';
      case Status.Disabled:
        return 'Excluída';
      default:
        throw new Error(`Status inválido: ${value}`);
    }
  }
}
