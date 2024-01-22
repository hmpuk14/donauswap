import { Component, OnInit, AfterViewInit } from '@angular/core';
import { OfferService } from '../services/offer.service';
import { CategoryService } from '../services/category.service';

declare var $: any;

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, AfterViewInit {
  categories: any[] = [];
  images: any[] = [];
  searchTerm: string = '';

  constructor(private offerService: OfferService, private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.loadOffers();
    this.loadCategories();
  }

  ngAfterViewInit(): void {
    setTimeout(() => {
      $('.slick-slider').slick({
        infinite: true,
        slidesToShow: 3,
        slidesToScroll: 3
      });
    }, 500);
  }

  loadOffers(): void {
    this.offerService.getOffers().subscribe(
      data => {
        console.log('Offers:', data);
        this.images = data;
      },
      error => {
        console.error('Error in HomeComponent:', error);
      }
    );
  }

  loadCategories(): void {
    this.categoryService.getCategories().subscribe(
      data => {
        this.categories = data;
      },
      error => {
        console.error('Error loading categories:', error);
      }
    );
  }

  searchOffers(): void {
    if (!this.searchTerm) {
      this.loadOffers();
      return;
    }

    this.offerService.searchOffers(this.searchTerm).subscribe(
      data => {
        console.log('Search results:', data);
        this.images = data;
      },
      error => {
        console.error('Error during search:', error);
      }
    );
  }

  onSearchTermChange(newTerm: string): void {
    this.searchTerm = newTerm;
    this.searchOffers();
  }

  getFilteredCategoriesFirstSet() {
    return this.categories.filter(category => category.id >= 1 && category.id <= 6);
  }

  getFilteredCategoriesSecondSet() {
    return this.categories.filter(category => category.id >= 7 && category.id <= 12);
  }

}
