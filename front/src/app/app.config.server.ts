import { mergeApplicationConfig, ApplicationConfig } from '@angular/core';
import { provideServerRendering } from '@angular/platform-server';
import { provideServerRouting } from '@angular/ssr';
import { appConfig } from './app.config';
import { serverRoutes } from './app.routes.server';
import { NgxMaskConfig, provideEnvironmentNgxMask } from 'ngx-mask';

const maskConfig: Partial<NgxMaskConfig> = {
  validation: false,
};
const serverConfig: ApplicationConfig = {
  providers: [
    provideServerRendering(),
    provideServerRouting(serverRoutes),
    provideEnvironmentNgxMask(),

  ]
};

export const config = mergeApplicationConfig(appConfig, serverConfig);
