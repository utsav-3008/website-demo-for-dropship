const products = [
  { name: "Smart LED Light", price: 1999 },
  { name: "Wireless Charger", price: 1499 },
  { name: "Kitchen Organizer", price: 999 },
  { name: "Fitness Resistance Band", price: 799 },
  { name: "Minimal Wallet", price: 1299 },
  { name: "Car Phone Holder", price: 699 }
];

const productList = document.getElementById("product-list");

products.forEach(product => {
  const card = document.createElement("div");
  card.className = "product-card";

  card.innerHTML = `
    <div class="product-img"></div>
    <h4>${product.name}</h4>
    <p>₹${product.price}</p>
    <button>Add to Cart</button>
  `;

  productList.appendChild(card);
});
