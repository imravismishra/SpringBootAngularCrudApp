import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FileUploadService } from '../fileupload.service';
import { FileDetails } from '../filedetails';


@Component({
  selector: 'app-uploadfile',
  templateUrl: './uploadfile.component.html',
  styleUrls: ['./uploadfile.component.css']
})
export class UploadFileComponent implements OnInit {

  fileDetails!: FileDetails;
  file!: File;
  fileUris: Array<string> = [];

  constructor(private fileUploadService: FileUploadService, private router: Router) { }

  ngOnInit(): void {
  }

  selectFile(event: any) {
    this.file = event.target.files.item(0);
    console.log(this.file);
  }

  uploadFile() {
    this.fileUploadService.upload(this.file).subscribe({
      next: (data) => {
        this.fileDetails = data;
        this.fileUris.push(this.fileDetails.fileUri);
        alert("File Uploaded Successfully")
      },
      error: (e) => {
        console.log(e);
      }
    });
  }
}