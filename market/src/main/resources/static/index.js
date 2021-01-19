angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

    // $scope.fillTable = function () {
    //     $http.get(contextPath + '/products')
    //         .then(function (response) {
    //             console.log(response);
    //             $scope.ProductsList = response.data;
    //         });
    // };
    $scope.productsOnPage = 10;

    $scope.fillTable = function () {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null,
                page: $scope.currentPageNum ? $scope.currentPageNum : 1
            }
        }).then(function (response) {
            $scope.ProductsList = response.data;
            $scope.listSize = $scope.ProductsList.length;
            $scope.numberOfPages = Math.ceil($scope.listSize / $scope.productsOnPage);
            let pagesNums = [];
            for (let i = 1; i <= $scope.numberOfPages; i++) {
                pagesNums[i - 1] = i;
            }
            $scope.pagesNums = pagesNums;
            $scope.ProductsOnCurrentPageList = $scope.ProductsList.slice(($scope.currentPageNum - 1) * $scope.productsOnPage,
                $scope.currentPageNum * $scope.productsOnPage);
            // console.log($scope.ProductsList.length);
            // console.log($scope.ProductsOnCurrentPageList.length);
            console.log($scope.ProductsList.slice(($scope.currentPageNum - 1) * $scope.productsOnPage));
        });
    };

    $scope.submitCreateNewProduct = function () {
        $http.post(contextPath + '/products', $scope.newProduct)
            .then(function (response) {
                // console.log('sended:');
                // console.log($scope.newProduct);
                // console.log('received');
                // console.log(response.data);
                $scope.newProduct = null;
                $scope.fillTable();
            });
    };

    $scope.deleteProductById = function (id) {
        $http.get(contextPath + '/products/delete/' + id)
            .then(function (response) {
                $scope.fillTable();
            })
    }

    $scope.changePage = function (pageNum) {
        $scope.currentPageNum = pageNum;
        $scope.fillTable();
    }

    $scope.changePage(1);

    $scope.fillTable();

})
;