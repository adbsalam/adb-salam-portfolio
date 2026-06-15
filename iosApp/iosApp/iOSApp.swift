import Shared
import SwiftUI

@main
struct iOSApp: App {
    init() {
        KmpBridge.shared.InitKoinForIos()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
