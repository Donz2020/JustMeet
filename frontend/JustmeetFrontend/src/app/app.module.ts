import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import {AppComponent} from './app.component';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {HomeComponent} from './home/home.component';
import {BoardAdminComponent} from './board-admin/board-admin.component';
import {ProfileComponent} from './profile/profile.component';
import {PostComponent} from './post/post.component';


import {SettingsComponent} from "./settings/settings.component";
import {ModalModule} from "./_modal";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {FormlyModule} from '@ngx-formly/core';
import {FormlyBootstrapModule} from '@ngx-formly/bootstrap';
import {MatStepperModule} from '@angular/material/stepper';

import {MatTabsModule} from "@angular/material/tabs";
import {AgmCoreModule} from '@agm/core';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    BoardAdminComponent,
    ProfileComponent,
    PostComponent,
    SettingsComponent
  ],
  imports: [
    BrowserAnimationsModule,
    MatStepperModule,
    FormlyBootstrapModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    ModalModule,
    MatTabsModule,
    AgmCoreModule.forRoot({

    }),
    FormlyModule.forRoot({
      validationMessages: [
        {name: 'required', message: 'This field is required'},
      ],
    }),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
