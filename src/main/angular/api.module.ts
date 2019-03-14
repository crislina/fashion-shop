import * as api from './api/api';
import * as angular from 'angular';

const apiModule = angular.module('api', [])
.service('LogcontrollerApi', api.LogcontrollerApi)
.service('OrdercontrollerApi', api.OrdercontrollerApi)
.service('ProductcontrollerApi', api.ProductcontrollerApi)

export default apiModule;
