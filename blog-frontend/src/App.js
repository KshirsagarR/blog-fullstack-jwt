import React, { useState } from "react";

function App() {
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [posts, setPosts] = useState([]);
  const [loggedIn, setLoggedIn] = useState(
    !!localStorage.getItem("token")
  );

const handleCreatePost = async () => {
  const response = await fetch("http://localhost:8083/posts", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      "Authorization": "Bearer " + localStorage.getItem("token")
    },
    body: JSON.stringify({ title, content })
  });

  if (response.ok) {
    setTitle("");
    setContent("");
    fetchMyPosts(); // refresh list
  } else {
    alert("Failed to create post");
  }
};

const handleDelete = async (postId) => {
  const response = await fetch(`http://localhost:8083/posts/${postId}`, {
    method: "DELETE",
    headers: {
      "Authorization": "Bearer " + localStorage.getItem("token")
    }
  });

  if (response.ok) {
    fetchMyPosts();
  } else {
    alert("Delete failed");
  }
};

const handleUpdate = async (post) => {
  const newTitle = prompt("New title:", post.title);
  const newContent = prompt("New content:", post.content);

  if (!newTitle || !newContent) return;

  const response = await fetch(`http://localhost:8083/posts/${post.id}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
      "Authorization": "Bearer " + localStorage.getItem("token")
    },
    body: JSON.stringify({
      title: newTitle,
      content: newContent
    })
  });

  if (response.ok) {
    fetchMyPosts();
  } else {
    alert("Update failed");
  }
};

  const handleLogin = async () => {
    try {
      const response = await fetch("http://localhost:8083/api/auth/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({ username, password })
      });

      const token = await response.text();

      if (response.ok) {
        localStorage.setItem("token", token);
        setLoggedIn(true);
      } else {
        alert("Login failed ❌");
      }

    } catch (error) {
      console.error(error);
    }
  };

  const fetchMyPosts = async () => {
    const response = await fetch(
      "http://localhost:8083/posts/my?page=0&size=5",
      {
        headers: {
          "Authorization": "Bearer " + localStorage.getItem("token")
        }
      }
    );

    const data = await response.json();
    setPosts(data.content);
  };

  const handleLogout = () => {
    localStorage.removeItem("token");
    setLoggedIn(false);
    setPosts([]);
  };

  //  CONDITIONAL RENDERING HERE
  if (!loggedIn) {
    return (
      <div style={{ padding: "40px" }}>
        <h1>Login</h1>

        <input
          type="text"
          placeholder="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
        <br /><br />

        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <br /><br />

        <button onClick={handleLogin}>Login</button>
      </div>
    );
  }

  //  IF LOGGED IN → SHOW POSTS PAGE
  return (
    <div style={{ padding: "40px" }}>
      <h1>My Posts</h1>
<button onClick={handleLogout} style={{ marginLeft: "1200px", marginRight:"0px",marginTop:"0px"}}>
        Logout
      </button>
      <h2>Create Post</h2>

<input
  type="text"
  placeholder="Title"
  value={title}
  onChange={(e) => setTitle(e.target.value)}
/>
<br /><br />

<textarea
  placeholder="Content"
  value={content}
  onChange={(e) => setContent(e.target.value)}
/>
<br /><br />

<button onClick={handleCreatePost}>Create</button>

<hr />




      <button onClick={fetchMyPosts}>Load My Posts</button>
      {/* <button onClick={handleLogout} style={{ marginLeft: "500px" }}>
        Logout
      </button> */}

     <ul>
  {posts.map((post) => (
    <li key={post.id}>
      <h3>{post.title}</h3>
      <p>{post.content}</p>

     <div style={{ display: "flex", gap: "10px" }}>
  <button onClick={() => handleUpdate(post)}>Update</button>
  <button onClick={() => handleDelete(post.id)}>Delete</button>
</div>
    </li>
  ))}
</ul>
    </div>
  );
}



export default App;