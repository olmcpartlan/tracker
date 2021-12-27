import React from 'react';
import { useState } from 'react';
import Modal from 'react-modal';
import Home from './Home';
import { theme } from './Theme'
import { BarChart, Moon, Sun, UserAdd } from "grommet-icons";
import { Grommet, Box, Button, Grid, Text, Form, TextInput } from 'grommet';


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


const overlayStyles = {
  content: {
    top: '25%',
    left: '25%',
    width: '50%',
    // background: { dark: 'dark-5', light: 'light-2' },
    height: '50%',
    right: 'auto',
    bottom: 'auto',
    // transform: 'translate(-50%, -50%)',
    // outerHeight: '100%'
  },
};

export default (props) => {
  const [sidebar, setSidebar] = useState(true);
  const [loggedin, setLoggedIn] = useState(true);
  const [lightMode, setLightMode] = useState(
    setSessionColor(true)
  );


  const [showOverlay, setOverlay] = useState(true);


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
          {lightMode ? <Sun onClick={() => setLightMode(false)} /> : <Moon onClick={() => setLightMode(true)} />}
          <UserAdd size='medium' onClick={(e) => showLogIn(e, setOverlay)} />
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
              gap={{row: 'medium'}}
              rows={['auto', 'small']}
              columns={['fill']}
              areas={[
                { name: 'input', start: [0, 0], end: [1, 0] },
                { name: 'button', start: [1, 1], end: [0, 1] },
              ]}

            >
              <Form>
                <Box height={"small"} gridArea="input" >
                  <TextInput placeholder="text" />
                  <TextInput placeholder="text" />

                </Box>
                <Box height="xxsmall" gridArea="button">
                  <Button
                    fill
                    primary
                    style={{ textAlign: "center" }}
                  >
                    hi!
                  </Button>

                </Box>
              </Form>
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



