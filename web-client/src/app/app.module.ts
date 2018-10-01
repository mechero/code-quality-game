import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {AppRoutingModule} from './app-routing.module';
import {TeamsComponent} from './teams/teams.component';
import {MembersComponent} from './members/members.component';
import {TeamsService} from './teams/teams.service';
import {MemberService} from './members/member.service';
import {OrganizerComponent} from "./settings/organizer.component";
import {SettingsComponent} from "./settings/settings.component";

@NgModule({
  declarations: [
    AppComponent,
    MembersComponent,
    TeamsComponent,
    OrganizerComponent,
    SettingsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [TeamsService, MemberService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
