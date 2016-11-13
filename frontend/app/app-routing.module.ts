import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TeamsComponent }   from './teams.component';
import { MembersComponent }      from './members.component';
const routes: Routes = [
    { path: '', redirectTo: '/teams', pathMatch: 'full' },
    { path: 'teams',  component: TeamsComponent },
    // { path: 'detail/:id', component: MemberDetailComponent },
    { path: 'members',     component: MembersComponent }
];
@NgModule({
    imports: [ RouterModule.forRoot(routes) ],
    exports: [ RouterModule ]
})
export class AppRoutingModule {}