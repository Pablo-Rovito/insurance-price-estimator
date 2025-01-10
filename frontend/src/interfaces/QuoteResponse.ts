import { CoverageEnum } from "../enums/CoverageEnum.ts";

export interface QuoteResponse {
    id: number;
    policyHolder: string;
    premium: number;
    coverageType: CoverageEnum;
}