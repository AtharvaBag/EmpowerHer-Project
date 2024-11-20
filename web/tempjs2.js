let activeRole = 'user'; // Store the current active role

    // Show and hide account form or dropdown
    document.getElementById('account-btn').onclick = (e) => {
        const dropdownMenu = document.getElementById("dropdown-menu");
        dropdownMenu.style.display = dropdownMenu.style.display === "block" ? "none" : "block";
    };

    document.getElementById('close-form').onclick = () => {
        document.querySelector('.account-form').classList.remove('active');
    };

    // Show appropriate form based on selected role
    const roleOptions = document.querySelectorAll('.role-option');
    roleOptions.forEach(option => {
        option.addEventListener('click', function () {
            const role = this.getAttribute('data-role');
            activeRole = role; // Set the active role
            showForm(role);
            document.querySelector('.account-form').classList.add('active'); // Show form
            document.getElementById("dropdown-menu").style.display = 'none'; // Hide dropdown after selection
        });
    });

    function showForm(role) {
        // Hide all forms
        document.querySelectorAll('.account-form form').forEach(form => form.classList.remove('active'));
        // Show the login form based on role
        if (role === 'user') {
            document.querySelector('.login-form').classList.add('active');
        } else if (role === 'admin') {
            document.querySelector('.admin-form').classList.add('active');
        } else if (role === 'trainer') {
            document.querySelector('.trainer-form').classList.add('active');
        }
    }

    // Toggle login and registration forms
    document.querySelector('.login-btn').onclick = () => {
        showForm(activeRole); // Show the login form for the current role
    };

    document.querySelector('.register-btn').onclick = () => {
        // Hide all forms
        document.querySelectorAll('.account-form form').forEach(form => form.classList.remove('active'));
        
        // Show registration form for the active role
        if (activeRole === 'user') {
            document.querySelector('.registration-form.user-form').classList.add('active');
        } else if (activeRole === 'admin') {
            document.querySelector('.registration-form.admin-form').classList.add('active');
        } else if (activeRole === 'trainer') {
            document.querySelector('.registration-form.trainer-form').classList.add('active');
        }
    };