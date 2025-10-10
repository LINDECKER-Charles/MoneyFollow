import { Routes } from '@angular/router';
import { LoginComponent } from './components/auth/login/login.component';
import { RegisterComponent } from './components/auth/register/register.component';
import { HomeComponent } from './components/misc/home/home.component';
import { NoAuthGuard } from './guards/no-auth.guard';
import { AboutComponent } from './components/misc/about/about.component';
import { ProfilComponent } from './components/user/profil/profil.component';
import { EditPasswordComponent } from './components/user/edit-password/edit-password.component';
import { EditEmailComponent } from './components/user/edit-email/edit-email.component';
import { EditNameComponent } from './components/user/edit-name/edit-name.component';

export const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'login', component: LoginComponent, canActivate: [NoAuthGuard] },
  { path: 'register', component: RegisterComponent, canActivate: [NoAuthGuard] },
  { path: 'home', component: HomeComponent },
  { path: 'profil', component: ProfilComponent },
  { path: 'profil/password-change', component: EditPasswordComponent },
  { path: 'profil/email-change', component: EditEmailComponent },
  { path: 'profil/name-change', component: EditNameComponent },
  { path: 'about', component: AboutComponent }
];
