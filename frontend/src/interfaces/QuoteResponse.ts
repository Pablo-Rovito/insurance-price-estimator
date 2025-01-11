import { CoverageEnum } from "../enums/CoverageEnum.ts";

export interface QuoteResponse {
    id: number;
    policyHolder: string;
    premium: number;
    coverageType: CoverageEnum;
    discount: Discount;
}

interface Discount {
    discountPercentage: number;
    endPrice: number;
}