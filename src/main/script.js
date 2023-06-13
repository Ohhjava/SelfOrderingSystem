const menuItemsContainer = document.getElementById('menu-items-container');
const orderItemsContainer = document.getElementById('order-items-container');
const totalAmountContainer = document.getElementById('total-amount-container');
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
    menuItems.forEach(item => {
        const menuItem = document.createElement('div');
        menuItem.classList.add('menu-item');
        menuItem.innerHTML = `
      <h3>${item.name}</h3>
      <p>${item.description}</p>
      <p>价格：$${item.price}</p>
      <button onclick="addToOrder(${item.id})">加入订单</button>
    `;
        menuItemsContainer.appendChild(menuItem);
    });
}

// 将菜品添加到订单
function addToOrder(itemId) {
    const item = menuItems.find(item => item.id === itemId);
    if (item) {
        orderItems.push(item);
        totalAmount += item.price;

        renderOrder();
    }
}

// 渲染订单
function renderOrder() {
    orderItemsContainer.innerHTML = '';
    orderItems.forEach(item => {
        const orderItem = document.createElement('div');
        orderItem.classList.add('order-item');
        orderItem.innerHTML = `
      <h3>${item.name}</h3>
      <p>价格：$${item.price}</p>
    `;
        orderItemsContainer.appendChild(orderItem);
    });

    totalAmountContainer.textContent = `总计：$${totalAmount.toFixed(2)}`;
}

// 提交订单
function submitOrder(event) {
    event.preventDefault();

    const name = document.getElementById('name').value;
    const contact = document.getElementById('contact').value;

    // 发送订单数据到后端进行处理
    const data = {
        name: name,
        contact: contact,
        items: orderItems
    };

    // 使用Ajax发送POST请求
    const xhr = new XMLHttpRequest();
    xhr.open('POST', 'backend/processOrder.php', true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.onload = function() {
        if (xhr.status === 200) {
            // 订单处理成功
            // 清空订单数据
            orderItems = [];
            totalAmount = 0;
            renderOrder();

            // 清空表单
            customerForm.reset();
            alert('订单已提交！');
        } else {
            // 订单处理失败
            alert('订单提交失败，请稍后再试！');
        }
    };
    xhr.send(JSON.stringify(data));
}

// 添加"修改"按钮和"取消"按钮的点击事件处理函数
function handleUpdateOrder(orderId) {
    // 获取新的状态，例如通过prompt或弹出模态框输入
    const newStatus = prompt("请输入新的订单状态:");
    if (newStatus) {
        // 发送Ajax请求到后端更新订单状态
        const xhr = new XMLHttpRequest();
        xhr.open('POST', '/updateOrder', true);
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                // 更新订单状态成功后的处理
                console.log('订单状态更新成功');
            }
        };
        const data = {
            orderId: orderId,
            newStatus: newStatus
        };
        xhr.send(JSON.stringify(data));
    }
}

function handleCancelOrder(orderId) {
    // 发送Ajax请求到后端取消订单
    const xhr = new XMLHttpRequest();
    xhr.open('POST', '/cancelOrder', true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // 取消订单成功后的处理
            console.log('订单已取消');
        }
    };
    const data = {
        orderId: orderId
    };
    xhr.send(JSON.stringify(data));
}

// 绑定"修改"按钮和"取消"按钮的点击事件
const updateButtons = document.getElementsByClassName("btn-update");
for (let i = 0; i < updateButtons.length; i++) {
    updateButtons[i].addEventListener("click", function() {
        const orderId = this.getAttribute("data-order-id");
        handleUpdateOrder(orderId);
    });
}

const cancelButtons = document.getElementsByClassName("btn-cancel");
for (let i = 0; i < cancelButtons.length; i++) {
    cancelButtons[i].addEventListener("click", function() {
        const orderId = this.getAttribute("data-order-id");
        handleCancelOrder(orderId);
    });
}


// 初始化
function init() {
    initMenu();
    renderOrder();

    customerForm.addEventListener('submit', submitOrder);
}

init();
