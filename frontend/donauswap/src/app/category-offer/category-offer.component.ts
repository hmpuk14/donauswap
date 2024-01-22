import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CategoryService } from '../services/category.service';

@Component({
  selector: 'app-category-offer',
  templateUrl: './category-offer.component.html',
  styleUrls: ['./category-offer.component.css']
})
export class CategoryOfferComponent implements OnInit {
  offersInCategory: any[] = [];
  noOffersFound: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private categoryService: CategoryService
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const categoryId = params.get('id');
      if (categoryId) {
        this.loadOffersByCategory(+categoryId);
      }
    });
  }

  loadOffersByCategory(categoryId: number): void {
    this.categoryService.getOffersByCategory(categoryId).subscribe(
      (data: any) => {
        this.offersInCategory = data;
        this.noOffersFound = this.offersInCategory === null || this.offersInCategory.length === 0;
      },
      (error: any) => {
        console.error('Error loading offers:', error);
        this.noOffersFound = true; // Wenn ein Fehler auftritt, setzen Sie noOffersFound auf true
      }
    );
  }
}
