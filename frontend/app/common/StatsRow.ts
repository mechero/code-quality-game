import {Badge} from './Badge';
export class StatsRow {
    userAlias: string;
    userTeam: string;
    totalPoints: number;
    totalPaidDebt: number;
    blocker: number;
    critical: number;
    major: number;
    minor: number;
    info: number;
    badges: Badge[];
}
