function decreaseQuantity() {
    var quantityInput = document.getElementById("quantityInput");
    var currentQuantity = parseInt(quantityInput.value);
    var minusButton = document.getElementById("minusButton");

    if (currentQuantity > 1) {
        quantityInput.value = currentQuantity - 1;
        updateTotalPrice();
    }

    // 수량이 1일 때 이미지 변경
    if (currentQuantity === 2) {
        minusButton.src = "/images/itemDetail/icon_minus_counter_inactive.png";
    }
}

function increaseQuantity() {
    var quantityInput = document.getElementById("quantityInput");
    var currentQuantity = parseInt(quantityInput.value);
    var minusButton = document.getElementById("minusButton");

    quantityInput.value = currentQuantity + 1;
    updateTotalPrice();

    // 수량이 2 이상일 때 이미지 변경
    if (currentQuantity === 1) {
        minusButton.src = "/images/itemDetail/icon_minus_counter.png";
    }
}

function updateTotalPrice() {
    var quantityInput = document.getElementById("quantityInput");
    var currentQuantity = parseInt(quantityInput.value);
    var priceText = document.getElementById("totalPriceText");

    var itemPrice = 31350; // 고정된 상품 가격

    var totalPrice = itemPrice * currentQuantity;
    priceText.textContent = totalPrice + "원";
}
