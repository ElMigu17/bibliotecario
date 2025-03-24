import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { UserComponent } from './components/user/user.component'

import { HttpClient, provideHttpClient } from '@angular/common/http';
import { AppComponent } from './app.component';
import { ReactiveFormsModule } from '@angular/forms';
import { AllCommunityModule, ModuleRegistry } from 'ag-grid-community'; 

// Register all Community features
ModuleRegistry.registerModules([AllCommunityModule]);

@NgModule({
    declarations: [
        AppComponent,
        UserComponent
    ],
    imports: [
        BrowserModule, 
        ReactiveFormsModule, 
        ModuleRegistry
    ],
    providers: [
        provideHttpClient()
    ],
    bootstrap: [AppComponent],
})
export class AppModule { }