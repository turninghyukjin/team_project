   function decreaseQuantity() {
        var quantityInput = document.getElementById("quantityInput");
        var currentQuantity = parseInt(quantityInput.value);

        if (currentQuantity > 1) {
            quantityInput.value = currentQuantity - 1;
            updateTotalPrice();
        }
    }

    function increaseQuantity() {
        var quantityInput = document.getElementById("quantityInput");
        var currentQuantity = parseInt(quantityInput.value);

        quantityInput.value = currentQuantity + 1;
        updateTotalPrice();
    }

    function updateTotalPrice() {
        var quantityInput = document.getElementById("quantityInput");
        var currentQuantity = parseInt(quantityInput.value);
        var priceText = document.getElementById("totalPriceText");

        var itemPrice = 31350; // 고정된 상품 가격

        var totalPrice = itemPrice * currentQuantity;
        priceText.textContent = totalPrice + "원";
    }