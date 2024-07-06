<template>
  <div class="profile">
      <img class="profile__avatar" :src="user.avatar" alt="User avatar">
      <h1 class="profile__name">{{ user.name }}</h1>
      <div class="profile__details">
          <p class="paragraph"><strong>Username:</strong> {{ user.username }}</p>
          <p class="paragraph"><strong>Email:</strong> {{ user.email }}</p>
          <p class="paragraph"><strong>Eliminar Perfil</strong></p>
          <!-- Add more details as needed -->
      </div>
  </div>
</template>

<script>
export default {
  name: 'Profile',
  data() {
    return {
      user: {
        name: 'User Name',
        avatar: 'path/to/avatar.jpg',
        username: 'username',
        email: 'user@example.com',
        // Add more details as needed
      },
    };
  },
};
</script>

<style scoped>
.profile {
  text-align: center;
}

.profile__name {
  font-weight: bold;
  text-align: center;
  margin-bottom: 20px;

}

.profile__avatar {
  text-align: center;
  margin-top: 100px;
}

.profile__details {

  text-align: center;
}.paragraph {
   margin-bottom: 25px;
}
</style>
