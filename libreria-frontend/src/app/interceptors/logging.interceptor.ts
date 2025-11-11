import { HttpInterceptorFn } from '@angular/common/http';
import { tap } from 'rxjs/operators';

export const loggingInterceptor: HttpInterceptorFn = (req, next) => {
  // Log request
  try {
    console.log('[HTTP] Request ->', req.method, req.urlWithParams, req.body);
  } catch (e) {
    console.log('[HTTP] Request ->', req.method, req.urlWithParams);
  }

  // Ensure Content-Type application/json for outgoing requests
  const jsonReq = req.clone({ setHeaders: { 'Content-Type': 'application/json' } });

  return next(jsonReq).pipe(
    tap({
      next: (event) => {
        // response events may be many; logging generic event
        console.log('[HTTP] Response event ->', event);
      },
      error: (err) => {
        console.error('[HTTP] Error ->', err);
      }
    })
  );
};
