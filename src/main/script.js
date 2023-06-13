// 获取DOM元素
const menuItemsContainer = document.getElementById('menu-items');
const orderItemsContainer = document.getElementById('order-items');
const totalAmountContainer = document.getElementById('total-amount');
const submitOrderButton = document.getElementById('submit-order');
const customerForm = document.getElementById('customer-form');

// 菜单数据
const menuItems = [
    { id: 1, name: 'Hamburger', price: 8.99, description: 'Delicious hamburger with all the fixings' },
    { id: 2, name: 'French Fries', price: 3.99, description: 'Crispy golden French fries' },
    // 可以根据需要添加更多菜单项
];

// 订单数据
let orderItems = [];
let totalAmount = 0;

// 初始化菜单
function initMenu() {
    menuItems.forEach(menuItem => {
        const menuItemElement = document.createElement('div');
        menuItemElement.classList.add('menu-item');
        menuItemElement.innerHTML = `
      <h3>${menuItem.name}</h3>
      <p>${menuItem.description}</p>
      <p>价格: $${menuItem.price}</p>
      <button onclick="addToOrder(${menuItem.id})">添加</button>
    `;
        menuItemsContainer.appendChild(menuItemElement);
    });
}

// 添加菜品到订单
function addToOrder(menuItemId) {
    const menuItem = menuItems.find(item => item.id === menuItemId);
    orderItems.push(menuItem);
    updateOrder();
}

// 更新订单
function updateOrder() {
    orderItemsContainer.innerHTML = '';
    totalAmount = 0;

    orderItems.forEach((item, index) => {
        const orderItemElement = document.createElement('div');
        orderItemElement.classList.add('order-item');
        orderItemElement.innerHTML = `
      <span>${item.name} - $${item.price}</span>
      <button onclick="removeFromOrder(${index})">移除</button>
    `;
        orderItemsContainer.appendChild(orderItemElement);
        totalAmount += item.price;
    });

    totalAmountContainer.textContent = `$${totalAmount.toFixed(2)}`;
}

// 从订单中移除菜品
function removeFromOrder(index) {
    orderItems.splice(index, 1);
    updateOrder();
}

// 提交订单
function submitOrder() {
    const name = document.getElementById('name').value;
    const contact = document.getElementById('contact').value;

    // 执行提交订单的逻辑，可以使用AJAX或者向后端发送请求

    // 重置订单和表单
    orderItems = [];
    updateOrder();
    customerForm.reset();
}

// 初始化页面
function init() {
    initMenu();
    updateOrder();

    // 提交订单按钮点击事件
    submitOrderButton.addEventListener('click', submitOrder);

    // 阻止表单默认提交行为
    customerForm.addEventListener('submit', event => {
        event.preventDefault();
        submitOrder();
    });
}

// 页面加载完成后初始化
window.addEventListener('DOMContentLoaded', init);
