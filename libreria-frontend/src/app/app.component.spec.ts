  import { TestBed } from '@angular/core/testing';
  import { AppComponent } from './app.component';

  describe('AppComponent', () => {
    beforeEach(async () => {
      await TestBed.configureTestingModule({
        imports: [AppComponent],
      }).compileComponents();
    });

    it('should create the app', () => {
      const fixture = TestBed.createComponent(AppComponent);
      const app = fixture.componentInstance;
      expect(app).toBeTruthy();
    });
    it('should have a loading property and render router outlet', () => {
      const fixture = TestBed.createComponent(AppComponent);
      const app = fixture.componentInstance as any;
      expect(typeof app.loading).toBe('boolean');
      fixture.detectChanges();
      const compiled = fixture.nativeElement as HTMLElement;
      // Ensure router-outlet exists in the rendered template
      expect(compiled.querySelector('router-outlet')).toBeTruthy();
    });
  });
