import {NgModule}      from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule}   from '@angular/forms';
import { Ng2BootstrapModule } from 'ng2-bootstrap/ng2-bootstrap';

import {MembersComponent}   from './members/members.component';
import {TeamsComponent} from './teams/teams.component';
import {AppComponent} from './app.component';
import {TeamsService} from './teams/teams.service';
import {MemberService} from './members/member.service';
import {AppRoutingModule} from './app-routing.module';

@NgModule({
    imports: [BrowserModule, FormsModule, AppRoutingModule, Ng2BootstrapModule],
    declarations: [AppComponent, MembersComponent, TeamsComponent],
    providers: [TeamsService, MemberService],
    bootstrap: [AppComponent]
})
export class AppModule {

}
