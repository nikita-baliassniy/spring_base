angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market/api/v1';

    $scope.fillTable = function (pageIndex = 1) {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                min_cost: $scope.filter ? $scope.filter.min_cost : null,
                max_cost: $scope.filter ? $scope.filter.max_cost : null,
                title: $scope.filter ? $scope.filter.title : null,
                p: pageIndex
            }
        }).then(function (response) {
            $scope.ProductsPage = response.data;
            let minPageIndex = pageIndex - 2;
            if (minPageIndex < 1) {
                minPageIndex = 1;
            }
            let maxPageIndex = pageIndex + 2;
            if (maxPageIndex > $scope.ProductsPage.totalPages) {
                maxPageIndex = $scope.ProductsPage.totalPages;
            }
            $scope.PaginationArray = $scope.generatePagesIndexes(minPageIndex, maxPageIndex)
        });
    };

    $scope.showCart = function () {
        $http({
            url: contextPath + '/cart',
            method: 'GET'
        }).then(function (response) {
            $scope.Cart = response.data;
            console.log($scope.Cart);
        });
    };

    $scope.addToCart = function (productId) {
        $http.get(contextPath + '/cart/add/' + productId)
            .then(function (response) {
                $scope.showCart();
            });
    }

    $scope.removeFromCart = function (productId) {
        $http.get(contextPath + '/cart/remove/' + productId)
            .then(function (response) {
                $scope.showCart();
            });
    }

    $scope.removeFromCartTotally = function (productId) {
        $http.get(contextPath + '/cart/removeTotal/' + productId)
            .then(function (response) {
                $scope.showCart();
            });
    }

    $scope.clearCart = function () {
        $http.get(contextPath + '/cart/clear')
            .then(function (response) {
                $scope.showCart();
            });
    }

    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.submitCreateNewProduct = function () {
        $http.post(contextPath + '/products', $scope.newProduct)
            .then(function (response) {
                $scope.newProduct = null;
                $scope.fillTable();
            });
    };

    $scope.deleteProductById = function (id) {
        $http.delete(contextPath + '/products/' + id)
            .then(function (response) {
                $scope.fillTable();
            })
    }

    $scope.fillTable();
    $scope.showCart();
})
;