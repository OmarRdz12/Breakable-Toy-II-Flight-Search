import GlobalContextProvider from "./context/GlobalContext"
import AppRoutes from "./routes"

function App() {
  return (
    <GlobalContextProvider>
      <div className="w-screen h-screen flex flex-col items-center p-4 gap-4">
        <AppRoutes />
      </div>
    </GlobalContextProvider>
  )
}

export default App
