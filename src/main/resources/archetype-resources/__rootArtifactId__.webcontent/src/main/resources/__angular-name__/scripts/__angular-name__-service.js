angular.module('${angular-name}')
    .factory("${angular-name}Services",
    ['$http', '$log',
        function ($http, $log) {
            var urlBase = '../webservices';
            return {

            };
        }])
    .config(['$httpProvider', function ($httpProvider) {
        //initialize get if not there
        if (!$httpProvider.defaults.headers.get) {
            $httpProvider.defaults.headers.get = {};
        }
        //disable IE ajax request caching
        $httpProvider.defaults.headers.get['Cache-Control'] = 'no-cache';
        $httpProvider.defaults.headers.get['Pragma'] = 'no-cache';
    }]);