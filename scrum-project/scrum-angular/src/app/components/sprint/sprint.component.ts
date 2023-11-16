import { MatDialog } from '@angular/material/dialog';
import { SprintService } from './../../services/sprint/sprint.service';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable, first } from 'rxjs';
import { Sprint } from 'src/app/models/sprint/sprint';
import { SprintFormModalComponent } from 'src/app/shared/components/sprint-form-modal/sprint-form-modal.component';

@Component({
  selector: 'app-sprint',
  templateUrl: './sprint.component.html',
  styleUrls: ['./sprint.component.scss'],
})
export class SprintComponent implements OnInit {
  sprints: Observable<Sprint[]> = new Observable<Sprint[]>();
  projetos = [];

  displayedColumns = [
    'title',
    'reporter',
    'description',
    'assignmentDate',
    'endDate',
    'actions',
  ];

  constructor(
    private sprintService: SprintService,
    public dialog: MatDialog,
    public snackBar: MatSnackBar
  ) {}

  onAdd() {
    const dialogRef = this.dialog.open(SprintFormModalComponent, {});

    dialogRef.afterClosed().subscribe(() => {
      this.loadSprints();
    });
  }

  onEdit(sprint: Sprint) {
    const dialogRef = this.dialog.open(SprintFormModalComponent, {
      data: sprint,
    });

    dialogRef.afterClosed().subscribe(() => {
      this.loadSprints();
    });
  }

  onRemove(sprint: Sprint) {
    this.sprintService.remove(sprint.id);
  }

  loadSprints() {
    this.sprints = this.sprintService.list().pipe(first());
  }

  ngOnInit() {
    this.loadSprints();
  }
}
