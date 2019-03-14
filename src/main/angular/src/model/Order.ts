/**
 * Api Documentation
 * Api Documentation
 *
 * OpenAPI spec version: 1.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

import * as models from './models';

export interface Order {
    "createdDate"?: Date;
    "id"?: number;
    "orderDetails"?: Array<models.OrderDetail>;
    "orderStatus"?: models.OrderStatus;
    "orderStatusId"?: number;
    "totalPrice"?: number;
}

