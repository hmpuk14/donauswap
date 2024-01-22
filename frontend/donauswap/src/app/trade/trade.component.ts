import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { TradeService } from '../services/trade.service';
import { AuthService } from '../services/auth.service';
import { CategoryService } from '../services/category.service';
import { User } from '../models/user.model';

// Einzelangebot erstellen mit Bilder und verschiedenste Eingaben
@Component({
  selector: 'app-trade',
  templateUrl: './trade.component.html',
  styleUrls: ['./trade.component.css']
})
export class TradeComponent implements OnInit {
  handelForm!: FormGroup;
  images: File[] = [];
  thumbnailUrls: string[] = [];
  maxImagesReached = false;
  imageUploadError: string | null = null;
  formSubmitted = false;
  categories: any[] = [];

  user: User | null = null;

  constructor(
    private fb: FormBuilder,
    private tradeService: TradeService,
    private authService: AuthService,
    private categoryService: CategoryService
  ) {}

  ngOnInit() {
    this.initForm();
    this.loadCategories();
    this.user = this.authService.getCurrentUser();
  }

  initForm() {
    this.handelForm = this.fb.group({
      mainImage: [null, Validators.required],
      description: ['', Validators.required],
      price: [null, Validators.required],
      rateUnit: ['', Validators.required],
      name: ['', Validators.required],
      tradeType: ['', Validators.required],
      condition: ['', Validators.required],
      category: ['', Validators.required]
    });
  }

  onFileSelect(event: Event) {
    const element = event.currentTarget as HTMLInputElement;
    let fileList: FileList | null = element.files;

    if (!fileList || fileList.length === 0) {
      return;
    }

    this.imageUploadError = null;

    if (this.images.length + fileList.length > 4) {
      this.maxImagesReached = true;
      return;
    } else {
      this.maxImagesReached = false;
    }

    for (let i = 0; i < fileList.length; i++) {
      const file = fileList[i];
      const reader = new FileReader();
      reader.onload = (e: any) => {
        const imgUrl = e.target.result;
        if (i === 0 && !this.handelForm.get('mainImage')?.value) {
          this.handelForm.patchValue({ mainImage: imgUrl });
          this.thumbnailUrls.unshift(imgUrl);
        } else {
          this.thumbnailUrls.push(imgUrl);
        }
        this.images.push(file);
      };
      reader.readAsDataURL(file);
    }
  }

  setMainImageFromThumbnail(imageUrl: string): void {
    this.handelForm.patchValue({ mainImage: imageUrl });
  }

  loadCategories() {
    this.categoryService.getCategories().subscribe(
      data => {
        this.categories = data;
      },
      error => {
        console.error('Fehler beim Laden der Kategorien', error); //Konsolen log war zur Fehlersuche notwendig
      }
    );
  }

  onSubmit() {
    this.formSubmitted = true;
    if (this.handelForm.valid) {
      const formData = new FormData();
      formData.append('mainImage', this.images[0]);
      for (let i = 1; i < this.images.length; i++) {
        formData.append('additionalImages', this.images[i]);
      }
      formData.append('description', this.handelForm.get('description')?.value);
      formData.append('price_dcoin', this.handelForm.get('price')?.value);
      formData.append('rateUnit', this.handelForm.get('rateUnit')?.value);
      formData.append('name', this.handelForm.get('name')?.value);
      formData.append('tradeType', this.handelForm.get('tradeType')?.value);
      formData.append('condition', this.handelForm.get('condition')?.value);
      formData.append('categoryId', this.handelForm.get('category')?.value);

      // E-Mail-Adresse des eingeloggten Benutzers hinzufügen
      if (this.user && this.user.email) {
        formData.append('userEmail', this.user.email);
      }

      this.tradeService.uploadImages(formData).subscribe(
        response => {
          console.log('Erfolg: Bilder erfolgreich hochgeladen', response);
          this.resetForm();
        },
        error => {
          console.error('Fehler beim Hochladen der Bilder', error);
        }
      );
    }
  }

  resetForm() {
    this.initForm();
    this.images = [];
    this.thumbnailUrls = [];
    this.formSubmitted = false;
  }
}
