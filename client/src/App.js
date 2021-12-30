import React, { useEffect, useReducer } from 'react';
import { BrowserRouter as Router, Routes, Route, useNavigate } from 'react-router-dom'
import { useState } from 'react';
import Modal from 'react-modal';
import Home from './Home';
import { theme } from './Theme'
import { BarChart, Moon, Sun, User, UserAdd } from "grommet-icons";
import { Grommet, Box, Button, Grid, Text, Form, TextInput, DateInput, Tip, Menu } from 'grommet';
import UserDashboard from './UserDashboard';


const showLogIn = (e, setOverlay) => {
  // TODO: logic to validate and communicate with db.
  setOverlay(true);
}

const setSessionColor = (value, defaultValue) => {
  const stored = sessionStorage.getItem('colorTheme');
  // The application starts in light mode by default. 
  // session storage won't be available.
  if (!stored) {
    return defaultValue;
  }

}


const afterOpenModal = () => {

}
const closeModal = (setOverlay) => {
  setOverlay(false);
}

const loginUser = (user, setUserLoggedIn) => {
  // call db to check credentials and add id to session.
  fetch(`/users/login?userName=${user.username}&passAttempt=${user.password}`)
    .then(res => res.json())
    .then(res => {
      window.sessionStorage.setItem("userId", res.userId);
      setUserLoggedIn(true);
      console.log(res);
    })

}

const createUser = (user, setUserLoggedIn) => {
  console.log(user);

  fetch("/users/create", {
    "method": "POST",
    "headers": {
      "content-type": "application/json"
    },
    "body": JSON.stringify(user)

  })
    .then(res => res.json())
    .then(res => {
      window.sessionStorage.setItem("userId", res.userId);
      setUserLoggedIn(true);
    });
}


const overlayStyles = {
  content: {
    top: '25%',
    left: '25%',
    width: '50%',
    // background: { dark: 'dark-5', light: 'light-2' },
    height: '50%',
    right: 'auto',
    bottom: 'auto',
    fontFamily: 'helvetica'
    // transform: 'translate(-50%, -50%)',
    // outerHeight: '100%'
  },
};


const App = (props) => {

  const [sidebar, setSidebar] = useState(true);
  const [loggedin, setLoggedIn] = useState((userId) => {
    window.sessionStorage.setItem("userId", userId);
  });
  const [lightMode, setLightMode] = useState(
    setSessionColor(true)
  );

  const [showOverlay, setOverlay] = useState(false);

  const [displayLoginForm, setShowLogin] = useState(false);


  useEffect(() => {
    const sessionId = window.sessionStorage.getItem("userId");

    console.log(`SESSION ID: ${sessionId}`);

    if (sessionId === 'undefined') {
      console.log('setting logged out.');
      setLoggedIn(false);
    }
    else setLoggedIn(true);
  }, [])


  const navigate = useNavigate();


  return <Grommet full theme={theme} background={lightMode ? 'light-3' : 'dark-1'} >
    {Grid.available ? (
      <Grid
        fill
        rows={['auto', 'flex']}
        columns={['auto', 'flex']}
        areas={[
          { name: 'header', start: [0, 0], end: [1, 0] },
          { name: 'sidebar', start: [0, 1], end: [0, 1] },
          { name: 'main', start: [1, 1], end: [1, 1] },
        ]}
      >
        <Box
          gridArea="header"
          direction="row"
          align="center"
          justify="between"
          pad={{ horizontal: 'medium', vertical: 'small' }}
          background='brand'
        >
          <Button onClick={() => setSidebar(!sidebar)}>
            <Text size="large">Title</Text>
          </Button>
          {lightMode
            ? <Sun onClick={() => setLightMode(false)} />
            : <Moon onClick={() => setLightMode(true)} />
          }
          {loggedin
            ? <Menu
              items={[
                { label: "profile", onClick: () => { navigate("/dashboard") } },
                { label: "nutrition data", onClick: () => { } }
              ]}
            >
              <User />
            </Menu>
            : <UserAdd size='medium' onClick={(e) => showLogIn(e, setOverlay)} />
          }
        </Box>

        {sidebar && (
          <Box
            gridArea="sidebar"
            background="dark-3"
            width="small"
            animation={[
              { type: 'fadeIn', duration: 300 },
              { type: 'slideRight', size: 'xlarge', duration: 150 },
            ]}
          >
            {['First', 'Second', 'Third'].map((name) => (
              <Button key={name} href="#" hoverIndicator>
                <Box pad={{ horizontal: 'medium', vertical: 'small' }}>
                  <Text>{name}</Text>
                </Box>
              </Button>
            ))}
          </Box>
        )}
        <Box gridArea="main" justify="center" align="center">
          <Modal
            isOpen={showOverlay}
            onAfterOpen={afterOpenModal}
            onRequestClose={() => closeModal(setOverlay)}
            style={overlayStyles}
            contentLabel="Example Modal"
          >

            <Grid
              gap={{ row: 'medium' }}
              rows={['auto', 'small']}
              columns={['fill']}
              areas={[
                { name: 'input', start: [0, 0], end: [1, 0] },
                { name: 'button', start: [1, 1], end: [0, 1] },
              ]}
            >

              {!displayLoginForm
                ?  <Form onSubmit={({ value }) => {
                createUser(value, setLoggedIn)
                closeModal(setOverlay)
              }}>
                <Box height={"small"} gridArea="input" >
                  <TextInput name="userName" type="text" placeholder="UserName" />
                  <TextInput name="email" type="email" placeholder="Email" />
                  <TextInput name="password" type="password" placeholder="Password" />
                </Box>
                <Box height="xxsmall" gridArea="button">
                  <Button
                    fill
                    primary
                    type="submit"
                    style={{ textAlign: "center" }}
                    onSubmit={(value) => {
                      // TODO: VALIDATE!!!!!!!!!!!!!!!
                      createUser(value);
                    }}
                  >
                    hi!
                  </Button>
                  <Box className="span-login">
                    <span onClick={() => setShowLogin(true)}>or login</span>
                  </Box>



                </Box>
              </Form>
                : <Form onSubmit={({ value }) => {
                  loginUser(value, setLoggedIn)
                  closeModal(setOverlay)
                }}>

                  <Box height={"small"} gridArea="input" >
                    <TextInput name="username" type="text" placeholder="username" />
                    <TextInput name="password" type="password" placeholder="password" />
                  </Box>
                  <Box height="xxsmall" gridArea="button">
                    <Button
                      fill
                      primary
                      type="submit"
                      style={{ textAlign: "center" }}
                    >
                      hi!
                    </Button>
                    <Box className="span-login">
                      <span onClick={() => setShowLogin(!showLogIn)}>or login</span>
                    </Box>



                  </Box>
                </Form>
              }

            </Grid>

          </Modal>

          <Home />
        </Box>
      </Grid>
    ) : (
      <Text>Grid is not supported by your browser</Text>
    )}
  </Grommet>


}

export default (props) => {

  return (
    <Router>
      <Routes>
        <Route path="/dashboard" exact element={<UserDashboard />} />
        <Route path="/" exact element={<App />} />
      </Routes>
    </Router>
  )



}



