import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {TeamsComponent} from './teams/teams.component';
import {MembersComponent} from './members/members.component';
import {OrganizerComponent} from "./settings/organizer.component";

const routes: Routes = [
  {path: '', redirectTo: '/teams', pathMatch: 'full'},
  {path: 'teams', component: TeamsComponent},
  // { path: 'detail/:id', component: MemberDetailComponent },
  {path: 'members', component: MembersComponent},
  {path: 'organizer', component: OrganizerComponent}
];

@NgModule({
  imports: [CommonModule, RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
