"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require('@angular/core');
var teams_service_1 = require('./teams.service');
var TeamsComponent = (function () {
    function TeamsComponent(teamsService) {
        this.teamsService = teamsService;
    }
    TeamsComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.teamsService.getTeams().then(function (teams) { return _this.teams = teams; });
    };
    TeamsComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            selector: 'teams',
            templateUrl: 'teams.component.html'
        }), 
        __metadata('design:paramtypes', [teams_service_1.TeamsService])
    ], TeamsComponent);
    return TeamsComponent;
}());
exports.TeamsComponent = TeamsComponent;
//# sourceMappingURL=teams.component.js.map